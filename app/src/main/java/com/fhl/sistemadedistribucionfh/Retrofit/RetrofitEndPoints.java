package com.fhl.sistemadedistribucionfh.Retrofit;

public class RetrofitEndPoints {


    public static final String URL_FH ="https://sistemasservice.trafficmanager.net/";
    public static final String URL_PRODMANFEST ="http://192.168.1.225:7420/App/Manifiesto/";//jose//"https://operacionsgdservice.trafficmanager.net/App/Manifiesto/";//  //http://192.168.1.227:7420/
    public static final String URL_PRODVEHICLES ="http://192.168.1.225:7420/App/Vehiculo/";//jose//"https://operacionsgdservice.trafficmanager.net/App/Vehiculo/";// //http://192.168.1.227:7420/
//=======
//    public static final String URL_FH ="https://sistemasservice.trafficmanager.net/";
//    public static final String URL_PRODMANFEST ="https://w5r7bljm-6220.usw3.devtunnels.ms/App/Manifiesto/";//"http://192.168.1.208:7420/App/Manifiesto/";//jose//"https://operacionsgdservice.trafficmanager.net/App/Manifiesto/";//  //http://192.168.1.227:7420/
//    public static final String URL_PRODVEHICLES ="https://w5r7bljm-6220.usw3.devtunnels.ms/App/Vehiculo/";//"";//jose//"https://operacionsgdservice.trafficmanager.net/App/Vehiculo/";// //http://192.168.1.227:7420/
//
//>>>>>>> main
    //ENDPOINTS FH
    public static final String URL_FHL_SGD="https://administracionsgdservice.trafficmanager.net/";
    public static final String URL_AVOCADO="http://35.194.83.10/WS-phoenixApiPROD/";
    //
    public static final String LOGIN = "Usuarios/Usuarios/Login";
    public static final String MENUSFH = "Catalogos/Modulos/GetListByUsuarioAplicacion/";
    //
    //ENDPOINTS PEP
    public static final String MANIFEST_PEP = "GetManifiestoByOperador/";
    public static final String TICKETS_PEP = "GetTicketsByManifiesto/";
    public static final String RATE_STARS ="SetCalificacionOperador/";
    public static final String PROFILE = "Usuarios/Usuarios/Values";//"todo" este enpoint no funciona correctamente en swagger debe verse desde postman
    public static final String DETALLE_TICKETS = "GetTicketsByManifiesto/";
    public static final String SALIDA_V2 = "GetSellosByManifiesto";
    public static final String CORTINA = "GetCortinasByManifiesto";
    public static final String CHECKLISTV2 = "GetChecklistByVehiculo" ;
    public static final String SETCHECKLISTBYVEHICULO = "SetChecklistByVehiculo";
    public static final String SETVALIDACION = "SetValidacionManifiesto" ;

//region endpoints entorno de pruebas

    public static final String URL_MAP_API = "http://newlandapps.com/";
    public static final String REASONS_SGD = "Despachos/CausaCambio/GetAll";
    public static final String LOCATION = "Vehiculos/Vehiculo/GetVehiculoAppMovil";
    public static final String SENDTRIPPLUS ="logistic/sendTripPlus";
    public static final String LOGINAVOCADO = "login/loginApp_v2";
    public static final String DETAIL_TICKET_SENDTRIPLUS = "GetTicketsByManifiesto";
    public static final String MANIFIESTO_SET_ESTATUS = "SetEstatusByManifiestoOrTicket";

// public static final String URL_NEWLANDS ="http://digimat-interactive.com/fhl/";
//    public static final String MENUS = "menu.php";
//    public static final String SALIDA = "salidaqr.php";
//    public static final String MANIFEST = "manifest.php";
//    public static final String CHECKLIST = "checklist.php" ;
//    public static final String GASTOS = "gastos.php" ;
//    public static final String RESGUARDO = "resguardo.php";
//    public static final String TICKETS = "tickets.php";
//    public static final String VALIDADOR = "validador.php";
//    public static final String REASONS = "reasons.php";
//endregion
}
