package co.edu.uniquindio.poo.billeteradigital.factory;

import co.edu.uniquindio.poo.billeteradigital.interfaces.*;
import co.edu.uniquindio.poo.billeteradigital.service.*;

public class ModelFactoryController {

    private static ModelFactoryController instance;

    private final IBilleteraDigitalService billeteraService;

    private ModelFactoryController() {
        IUsuarioService usuarioService = new UsuarioService();
        ICuentaService cuentaService = new CuentaService();
        ITransaccionService transaccionService = new TransaccionService();
        ICategoriaService categoriaService = new CategoriaService();
        IPresupuestoService presupuestoService = new PresupuestoService();
        IEstadisticaService estadisticaService = new EstadisticaService();

        billeteraService = new BilleteraDigitalService(
                usuarioService,
                cuentaService,
                transaccionService,
                categoriaService,
                presupuestoService,
                estadisticaService
        );
    }

    public static ModelFactoryController getInstance() {
        if (instance == null) {
            instance = new ModelFactoryController();
        }
        return instance;
    }

    public IBilleteraDigitalService getBilleteraService() {
        return billeteraService;
    }
}
