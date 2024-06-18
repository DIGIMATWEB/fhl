package com.fhl.sistemadedistribucionfh.ZmapV2util;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class mapV2Util {
    public static List<TripV2> buildGoogleUrlImage(List<TripV2> tripsList, Context context) {
        String urlMapImage = "";
        String baseUrlImageMap = "http://maps.googleapis.com/maps/api/staticmap?";
        String sizeImageMap = "size=600x600&";
        String googleKey = "key=" + context.getString(R.string.keyGoogleMaps) + "&";

        String markerStar = "markers=color:black|label:s|";
        String latitudeMarkerStart = "";
        String longitudeMarkerStart = "";

        String markerEnd = "markers=icon:https://cdn.zeplin.io/5d51a93546e50e9b42934a50/assets/C06FE264-944C-421A-8E01-68646B10D08F.png|label:s|";
        String latitudeMarkerEnd = "";
        String longitudeMarkerEnd = "";

        String path = "&path=color:0x000000ff|weight:3%7Cenc:";
        String baseUlrPolyline = "path=color:0x000000ff|weight:3|";
        String sensor = "sensor=false";

        List<LatLng> recorridos = new ArrayList<>();
        List<PositionV2> positions;
        for (int i = 0; i < tripsList.size(); i++) {
            positions = tripsList.get(i).getPositions();

            if (positions != null && positions.size() > 0) {
                PositionV2 startPosition = positions.get(0);
                latitudeMarkerStart = startPosition.getLatitude() + "";
                longitudeMarkerStart = startPosition.getLongitude() + "";

                PositionV2 endPosition = positions.get(positions.size() - 1);
                latitudeMarkerEnd = endPosition.getLatitude() + "";
                longitudeMarkerEnd = endPosition.getLongitude() + "";

                for (int j = 0; j < positions.size(); j++) {
                    recorridos.add(new LatLng(positions.get(j).getLatitude(), positions.get(j).getLongitude()));

                }
                // Log.e("mydaytrips",""+recorridos);
                String encode = PolyUtil.encode(recorridos);
                String googleImage = baseUrlImageMap + sizeImageMap + googleKey + markerStar + latitudeMarkerStart + "," + longitudeMarkerStart + "&" + markerEnd + latitudeMarkerEnd + "," + longitudeMarkerEnd + "&" + path + encode;

                tripsList.get(i).setUrlMapImage(googleImage);
                recorridos.clear();
            }

        }

        return tripsList;
    }
}
