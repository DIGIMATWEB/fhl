package com.fhl.sistemadedistribucionfh.login.model.roles;

import com.google.gson.annotations.SerializedName;

public class responseUserRole {
    @SerializedName("status")
    
    private Integer status;
    @SerializedName("message")
    
    private String message;
    @SerializedName("data")
    
    private dataUserRole data;

    public responseUserRole(Integer status, String message, dataUserRole data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public dataUserRole getData() {
        return data;
    }

    public void setData(dataUserRole data) {
        this.data = data;
    }

}

/*ejemplo
{
        "status": 200,
        "message": "Proceso ejecutado correctamente",
        "data": {
        "nombre": "JOSE ALFREDO ESPEJEL ALFARO",
        "rfc": "AAEA930427KV8",
        "identificacion": "1380",
        "tipoPerfilesId": 1,
        "centroDistribucionesId": 1,
        "nss": null,
        "correoElectronico": "joj_alfredo@hotmail.com ",
        "telefono": null,
        "imei": "11111111111111111",
        "estado": true,
        "comentarios": "",
        "ultimoAcceso": "2024-04-03T13:04:07.847",
        "usuario": "usrPhoenixAdmin",
        "tipoPerfiles": {
        "nombre": "Operador",
        "usuario": "usrPhoenixAdmin",
        "id": 1,
        "fechaCreacion": "0001-01-01T00:00:00",
        "eliminado": false,
        "esNuevo": false
        },
        "id": 6,
        "eliminado": true,
        "esNuevo": false
        }
        }
        */
