
package com.bmc.suchane_svamitva.api;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AccessTokenAuthenticator implements Authenticator {
    private final Context context;

    public AccessTokenAuthenticator(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, @NonNull Response response) {
        final String accessToken = TokenUtility.getSavedToken(context);
        if (!isRequestWithAccessToken(response) || accessToken == null) {
            return null;
        }
        synchronized (this) {
            final String newAccessToken = TokenUtility.getSavedToken(context);
            // Access token is refreshed in another thread.
            if (!accessToken.equals(newAccessToken)) {
                return newRequestWithAccessToken(response.request(), newAccessToken);
            }

            // Need to refresh an access token
            final String updatedAccessToken = TokenUtility.getAccessToken(context);
            return newRequestWithAccessToken(response.request(), updatedAccessToken);
        }
    }

    private boolean isRequestWithAccessToken(@NonNull Response response) {
        String header = response.request().header("Authorization");
        return header != null && header.startsWith("bearer");
    }


    @NonNull
    private Request newRequestWithAccessToken(@NonNull Request request, @NonNull String accessToken) {
        return request.newBuilder()
                .header("Authorization", "bearer " + accessToken)
                .build();
    }
}



