package com.factory;

import com.DAO.*; 


public class DaoFactory {

    // 1. Usuarios
    public static IusuarioDao getUsuarioDao() {
        return new IusuarioDaoImplement();
    }

    // 2. Galpones
    public static IgalponDao getGalponDao() {
        return new IgalponDaoImplement();
    }

    // 3. Faenas
    public static IfaenaDao getFaenaDao() {
        return new IfaenaDaoImplement();
    }

    // 4. Clientes
    public static IclienteDao getClienteDao() {
        return new IclienteDaoImplement();
    }

    // 5. Insumos compras
    public static IinsumoDao getInsumoDao() {
        return new IinsumoDaoImplement();
    }

    // 6. Cámaras Frigoríficas
    public static IcamaraFrigorificaDao getCamaraFrigorificaDao() {
        return new IcamaraFrigorificaDaoImplement();
    }

    // 7. Controles Sanidad
    public static IcontrolSanidadDao getControlSanidadDao() {
        return new IcontrolSanidadDaoImplement();
    }

    // 8. Detalles Ventas
    public static IdetalleVentaDao getDetalleVentaDao() {
        return new IdetalleVentaDaoImplement();
    }

    // 9. Lotes Aves
    public static IloteAvesDao getLoteAvesDao() {
        return new IloteAvesDaoImplement();
    }

    // 10. Medición Sensores
    public static ImedicionSensorDao getMedicionSensorDao() {
        return new ImedicionSensorDaoImplement();
    }

    // 11. Productos Stock
    public static IproductoStockDao getProductoStockDao() {
        return new IproductoStockDaoImplement();
    }

    // 12. Proveedores
    public static IproveedorDao getProveedorDao() {
        return new IproveedorDaoImplement();
    }

    // 13. Roles
    public static IrolDao getRolDao() {
        return new IrolDaoImplement();
    }

    // 14. Sensores
    public static IsensorDao getSensorDao() {
        return new IsensorDaoImplement();
    }

    // 15. Ventas
    public static IventaDao getVentaDao() {
        return new IventaDaoImplement();
    }
}
