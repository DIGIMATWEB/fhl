package com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataQuestions {
    @SerializedName("VehiculoId")
    private Integer vehiculoId;
    @SerializedName("VehiculoVsChecklist")
    private List<VehiculoVsCheck> vehiculoVsChecklist;
    @SerializedName("Id")
    private Integer id;

    public dataQuestions(Integer vehiculoId, List<VehiculoVsCheck> vehiculoVsChecklist, Integer id) {
        super();
        this.vehiculoId = vehiculoId;
        this.vehiculoVsChecklist = vehiculoVsChecklist;
        this.id = id;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }



    public List<VehiculoVsCheck> getVehiculoVsChecklist() {
        return vehiculoVsChecklist;
    }

    public void setVehiculoVsChecklist(List<VehiculoVsCheck> vehiculoVsChecklist) {
        this.vehiculoVsChecklist = vehiculoVsChecklist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
