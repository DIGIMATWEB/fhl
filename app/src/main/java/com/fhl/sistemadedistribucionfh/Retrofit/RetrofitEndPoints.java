package com.fhl.sistemadedistribucionfh.Retrofit;

public class RetrofitEndPoints {
    public static final String URL_MAP_API = "http://newlandapps.com/";
    public static final String URL_FH = "https://sistemasservice.trafficmanager.net/";
    public static final String URL_NEWLANDS ="http://digimat-interactive.com/fhl/";
    public static final String URL_MAP_API_ROUTE = "route";

    public static final String URL_PEPSERVER = "http://pepserver:7420/app/manifiesto/";
    //public static final String URL_PEPSERVER = "http://192.168.1.209:7420/app/manifiesto/";


    //ENDPOINTS FH
    public static final String LOGIN = "Usuarios/Usuarios/Login";
    public static final String MENUSFH = "Catalogos/Modulos/GetListByUsuarioAplicacion/";

    //ENDPOINTS PEP
    public static final String MANIFEST_PEP = "GetManifiestoByOperador/";
    public static final String TICKETS_PEP = "GetTicketsByManifiesto/";

    public static final String MENUS = "menu.php";
    public static final String SALIDA = "salidaqr.php";
    public static final String MANIFEST = "manifest.php";
    public static final String CHECKLIST = "checklist.php" ;
    public static final String GASTOS = "gastos.php" ;
    public static final String RESGUARDO = "resguardo.php";
    public static final String TICKETS = "tickets.php";
    public static final String VALIDADOR = "validador.php";
    public static final String REASONS = "reasons.php";
    public static final String PROFILE = "Usuarios/Usuarios/Values";//"todo" este enpoint no funciona correctamente en swagger debe verse desde postman
    public static final String DETALLE_TICKETS = "GetTicketsByManifiesto/";
    public static final String SALIDA_V2 = "GetSellosByManifiesto/";
    public static final String CORTINA = "GetCortinasByManifiesto";
}
