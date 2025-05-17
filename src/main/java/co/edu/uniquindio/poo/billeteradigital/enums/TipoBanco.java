package co.edu.uniquindio.poo.billeteradigital.enums;

public enum TipoBanco {
    DAVIVIENDA("Banco Davivienda: servicios financieros tradicionales."),
    BANCOLOMBIA("Bancolombia: cobertura nacional y digital."),
    BBVA("BBVA Colombia: parte del grupo bancario internacional."),
    NEQUI("Nequi: banca digital, sin oficinas físicas."),
    DAVIPLATA("Daviplata: cuenta de ahorro fácil y móvil.");

    private final String descripcion;

    TipoBanco(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

