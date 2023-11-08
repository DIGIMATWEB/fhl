package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

public class VehiculoDataV2 {
    @SerializedName("placa")
    private String placa;
    @SerializedName("economico")
    private String economico;
    @SerializedName("vin")
    private String vin;
    @SerializedName("anio")
    private Integer anio;
    @SerializedName("tanqueCombustible")
    private Integer tanqueCombustible;
    @SerializedName("rendimientoMixto")
    private String rendimientoMixto;
    @SerializedName("rendimientoUrbano")
    private String rendimientoUrbano;
    @SerializedName("rendimientoSuburbano")
    private String rendimientoSuburbano;
    @SerializedName("capacidadVolumen")
    private String capacidadVolumen;
    @SerializedName("capacidadVolumenEfectivo")
    private String capacidadVolumenEfectivo;
    @SerializedName("factura")
    private String factura;
    @SerializedName("facturaCarrocero")
    private String facturaCarrocero;
    @SerializedName("grupoVehiculo")
    private String grupoVehiculo;
    @SerializedName("proveedorSeguroId")
    private String proveedorSeguroId;
    @SerializedName("polizaSeguro")
    private String polizaSeguro;
    @SerializedName("inciso")
    private String inciso;
    @SerializedName("prima")
    private String prima;
    @SerializedName("numPermiso")
    private String numPermiso;
    @SerializedName("tipoPermiso")
    private String tipoPermiso;
    @SerializedName("habilidadVehiculos")
    private String habilidadVehiculos;
    @SerializedName("colaboradorId")
    private String colaboradorId;
    @SerializedName("rangoOperacion")
    private String rangoOperacion;
    @SerializedName("maniobras")
    private String maniobras;
    @SerializedName("un")
    private String un;
    @SerializedName("motor")
    private String motor;
    @SerializedName("factorCo2")
    private String factorCo2;
    @SerializedName("tagCaseta")
    private String tagCaseta;
    @SerializedName("ultimoOdometro")
    private String ultimoOdometro;
    @SerializedName("usuario")
    private String usuario;
    @SerializedName("estado")
    private String estado;
    @SerializedName("configuracionId")
    private String configuracionId;
    @SerializedName("configuracion")
    private String configuracion;
    @SerializedName("esquemaId")
    private String esquemaId;
    @SerializedName("esquema")
    private String esquema;
    @SerializedName("proveedorId")
    private String proveedorId;
    @SerializedName("proveedor")
    private String proveedor;
    @SerializedName("tipoCombustibleId")
    private String tipoCombustibleId;
    @SerializedName("tipoCombustible")
    private String tipoCombustible;
    @SerializedName("tipoId")
    private String tipoId;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("marcaId")
    private Integer marcaId;
    @SerializedName("marca")
    private MarcaDataV2 marca;
    @SerializedName("modeloId")
    private Integer modeloId;
    @SerializedName("modelo")
    private ModeloDataV2 modelo;
    @SerializedName("colorId")
    private String colorId;
    @SerializedName("color")
    private String color;
    @SerializedName("proveedorSeguro")
    private String proveedorSeguro;
    @SerializedName("colaborador")
    private String colaborador;
    @SerializedName("id")
    private Integer id;
    @SerializedName("trail")
    private String trail;
    @SerializedName("fechaCreacion")
    private String fechaCreacion;
    @SerializedName("eliminado")
    private Boolean eliminado;
    @SerializedName("esNuevo")
    private Boolean esNuevo;

    public VehiculoDataV2(String placa, String economico, String vin, Integer anio, Integer tanqueCombustible, String rendimientoMixto, String rendimientoUrbano, String rendimientoSuburbano, String capacidadVolumen, String capacidadVolumenEfectivo, String factura, String facturaCarrocero, String grupoVehiculo, String proveedorSeguroId, String polizaSeguro, String inciso, String prima, String numPermiso, String tipoPermiso, String habilidadVehiculos, String colaboradorId, String rangoOperacion, String maniobras, String un, String motor, String factorCo2, String tagCaseta, String ultimoOdometro, String usuario, String estado, String configuracionId, String configuracion, String esquemaId, String esquema, String proveedorId, String proveedor, String tipoCombustibleId, String tipoCombustible, String tipoId, String tipo, Integer marcaId, MarcaDataV2 marca, Integer modeloId, ModeloDataV2 modelo, String colorId, String color, String proveedorSeguro, String colaborador, Integer id, String trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.placa = placa;
        this.economico = economico;
        this.vin = vin;
        this.anio = anio;
        this.tanqueCombustible = tanqueCombustible;
        this.rendimientoMixto = rendimientoMixto;
        this.rendimientoUrbano = rendimientoUrbano;
        this.rendimientoSuburbano = rendimientoSuburbano;
        this.capacidadVolumen = capacidadVolumen;
        this.capacidadVolumenEfectivo = capacidadVolumenEfectivo;
        this.factura = factura;
        this.facturaCarrocero = facturaCarrocero;
        this.grupoVehiculo = grupoVehiculo;
        this.proveedorSeguroId = proveedorSeguroId;
        this.polizaSeguro = polizaSeguro;
        this.inciso = inciso;
        this.prima = prima;
        this.numPermiso = numPermiso;
        this.tipoPermiso = tipoPermiso;
        this.habilidadVehiculos = habilidadVehiculos;
        this.colaboradorId = colaboradorId;
        this.rangoOperacion = rangoOperacion;
        this.maniobras = maniobras;
        this.un = un;
        this.motor = motor;
        this.factorCo2 = factorCo2;
        this.tagCaseta = tagCaseta;
        this.ultimoOdometro = ultimoOdometro;
        this.usuario = usuario;
        this.estado = estado;
        this.configuracionId = configuracionId;
        this.configuracion = configuracion;
        this.esquemaId = esquemaId;
        this.esquema = esquema;
        this.proveedorId = proveedorId;
        this.proveedor = proveedor;
        this.tipoCombustibleId = tipoCombustibleId;
        this.tipoCombustible = tipoCombustible;
        this.tipoId = tipoId;
        this.tipo = tipo;
        this.marcaId = marcaId;
        this.marca = marca;
        this.modeloId = modeloId;
        this.modelo = modelo;
        this.colorId = colorId;
        this.color = color;
        this.proveedorSeguro = proveedorSeguro;
        this.colaborador = colaborador;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEconomico() {
        return economico;
    }

    public void setEconomico(String economico) {
        this.economico = economico;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getTanqueCombustible() {
        return tanqueCombustible;
    }

    public void setTanqueCombustible(Integer tanqueCombustible) {
        this.tanqueCombustible = tanqueCombustible;
    }

    public String getRendimientoMixto() {
        return rendimientoMixto;
    }

    public void setRendimientoMixto(String rendimientoMixto) {
        this.rendimientoMixto = rendimientoMixto;
    }

    public String getRendimientoUrbano() {
        return rendimientoUrbano;
    }

    public void setRendimientoUrbano(String rendimientoUrbano) {
        this.rendimientoUrbano = rendimientoUrbano;
    }

    public String getRendimientoSuburbano() {
        return rendimientoSuburbano;
    }

    public void setRendimientoSuburbano(String rendimientoSuburbano) {
        this.rendimientoSuburbano = rendimientoSuburbano;
    }

    public String getCapacidadVolumen() {
        return capacidadVolumen;
    }

    public void setCapacidadVolumen(String capacidadVolumen) {
        this.capacidadVolumen = capacidadVolumen;
    }

    public String getCapacidadVolumenEfectivo() {
        return capacidadVolumenEfectivo;
    }

    public void setCapacidadVolumenEfectivo(String capacidadVolumenEfectivo) {
        this.capacidadVolumenEfectivo = capacidadVolumenEfectivo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getFacturaCarrocero() {
        return facturaCarrocero;
    }

    public void setFacturaCarrocero(String facturaCarrocero) {
        this.facturaCarrocero = facturaCarrocero;
    }

    public String getGrupoVehiculo() {
        return grupoVehiculo;
    }

    public void setGrupoVehiculo(String grupoVehiculo) {
        this.grupoVehiculo = grupoVehiculo;
    }

    public String getProveedorSeguroId() {
        return proveedorSeguroId;
    }

    public void setProveedorSeguroId(String proveedorSeguroId) {
        this.proveedorSeguroId = proveedorSeguroId;
    }

    public String getPolizaSeguro() {
        return polizaSeguro;
    }

    public void setPolizaSeguro(String polizaSeguro) {
        this.polizaSeguro = polizaSeguro;
    }

    public String getInciso() {
        return inciso;
    }

    public void setInciso(String inciso) {
        this.inciso = inciso;
    }

    public String getPrima() {
        return prima;
    }

    public void setPrima(String prima) {
        this.prima = prima;
    }

    public String getNumPermiso() {
        return numPermiso;
    }

    public void setNumPermiso(String numPermiso) {
        this.numPermiso = numPermiso;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public String getHabilidadVehiculos() {
        return habilidadVehiculos;
    }

    public void setHabilidadVehiculos(String habilidadVehiculos) {
        this.habilidadVehiculos = habilidadVehiculos;
    }

    public String getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(String colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public String getRangoOperacion() {
        return rangoOperacion;
    }

    public void setRangoOperacion(String rangoOperacion) {
        this.rangoOperacion = rangoOperacion;
    }

    public String getManiobras() {
        return maniobras;
    }

    public void setManiobras(String maniobras) {
        this.maniobras = maniobras;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getFactorCo2() {
        return factorCo2;
    }

    public void setFactorCo2(String factorCo2) {
        this.factorCo2 = factorCo2;
    }

    public String getTagCaseta() {
        return tagCaseta;
    }

    public void setTagCaseta(String tagCaseta) {
        this.tagCaseta = tagCaseta;
    }

    public String getUltimoOdometro() {
        return ultimoOdometro;
    }

    public void setUltimoOdometro(String ultimoOdometro) {
        this.ultimoOdometro = ultimoOdometro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(String configuracionId) {
        this.configuracionId = configuracionId;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public String getEsquemaId() {
        return esquemaId;
    }

    public void setEsquemaId(String esquemaId) {
        this.esquemaId = esquemaId;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipoCombustibleId() {
        return tipoCombustibleId;
    }

    public void setTipoCombustibleId(String tipoCombustibleId) {
        this.tipoCombustibleId = tipoCombustibleId;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public MarcaDataV2 getMarca() {
        return marca;
    }

    public void setMarca(MarcaDataV2 marca) {
        this.marca = marca;
    }

    public Integer getModeloId() {
        return modeloId;
    }

    public void setModeloId(Integer modeloId) {
        this.modeloId = modeloId;
    }

    public ModeloDataV2 getModelo() {
        return modelo;
    }

    public void setModelo(ModeloDataV2 modelo) {
        this.modelo = modelo;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProveedorSeguro() {
        return proveedorSeguro;
    }

    public void setProveedorSeguro(String proveedorSeguro) {
        this.proveedorSeguro = proveedorSeguro;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Boolean getEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(Boolean esNuevo) {
        this.esNuevo = esNuevo;
    }
}