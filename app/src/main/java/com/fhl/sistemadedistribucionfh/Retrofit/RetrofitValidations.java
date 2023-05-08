package com.fhl.sistemadedistribucionfh.Retrofit;

import android.content.Context;

import java.io.IOException;
import com.fhl.sistemadedistribucionfh.R;
public class RetrofitValidations {

    /**
     * Validate responseCode
     *
     * @param responseCode
     * @return
     */
    public static boolean checkSuccessCode(int responseCode) {
        if (responseCode == RetrofitConstants.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 400 Bad Request.
     * 401 Unauthorized.
     * 402 Payment Required.
     * 403 Forbidden.
     * 404 Not Found.
     * 500 Internal Server Error.
     */

    //public String getErrorByStatus(int responseCode, Context context) {
    public static String getErrorByStatus(int responseCode, Context context) {
        String error;
        if (responseCode == RetrofitConstants.BAD_CODE) {
            error = context.getString(R.string.wrongSyntax);
        } else if (responseCode == RetrofitConstants.UNAUTHORIZED_CODE) {
            error = context.getString(R.string.unauthorizedRequest);
        } else if (responseCode == RetrofitConstants.NOT_FOUND_CODE) {
            error = context.getString(R.string.resourceNotFound);
        } else if (responseCode == RetrofitConstants.INTERNAL_SERVER_ERROR_CODE) {
            error = context.getString(R.string.serverError);
        } else {
            error = context.getString(R.string.unknownError);
        }
        return error;
    }

    // public String getOnFailureResponse(Context context, Throwable t, int requestType) {
    public static String getOnFailureResponse(Throwable t, Context context) {
        if (t instanceof IOException) {
            return context.getString(R.string.internetConection);
        } else if (t instanceof IllegalStateException) {
            return context.getString(R.string.invalidateFormatResponse);
        } else {
            return context.getString(R.string.unknownError1);
        }
    }
}
