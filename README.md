# Tercer Modelo de Parcial

---

# 🚗 Sistema de Gestión para Taller de Reparación de Vehículos

## 📄 Descripción del Proyecto

Este proyecto consiste en el desarrollo de un sistema para gestionar un taller de reparación de vehículos, donde se manejan diferentes tipos de vehículos con características particulares. El sistema permite registrar y gestionar las reparaciones, calcular los costos de servicio y llevar un historial de reparaciones detallado de cada vehículo. Además, se controla la disponibilidad de los vehículos y se permite realizar consultas sobre su estado actual y su historial de reparaciones.


## 🛠️ Funcionalidades del Sistema

### 1. Gestión de Vehículos

- **Tipos de Vehículos**: Manejo de vehículos compactos y SUV, cada uno con características específicas como año de fabricación o tracción integral.
- **Estados del Vehículo**: Control del estado del vehículo (DISPONIBLE, EN_REPARACION, FUERA_DE_SERVICIO).
- **Historial de Reparaciones**: Registro detallado de cada reparación realizada al vehículo, con fecha, taller y descripción.

### 2. Gestión de Reparaciones

- **Iniciar y Finalizar Reparaciones**: Registro del inicio y finalización de reparaciones, actualizando el estado del vehículo.
- **Cálculo de Costo de Reparaciones**: Cálculo automático del costo de la reparación basado en las horas trabajadas y las características del vehículo (como año de fabricación o tracción integral).

### 3. Manejo de Excepciones

- **Validación del Código del Vehículo**: Validación de que cada vehículo tiene un código único de exactamente 7 caracteres. Se lanza una excepción en caso de que no cumpla con esta condición.
- **Excepciones de Estado del Vehículo**: El sistema lanza excepciones si se intenta iniciar una reparación de un vehículo que ya está en reparación o si se finaliza una reparación para un vehículo que no está en ese estado.

## 📦 Estructura del Proyecto

### 1. **Clase Vehiculo (Abstracta)**

- **Atributos**:
    - `codigoVehiculo`: Código único de 7 caracteres del vehículo (validado con excepción).
    - `modelo`: Modelo del vehículo.
    - `precioBase`: Precio base del servicio de reparación.
    - `estado`: Estado actual del vehículo (`DISPONIBLE`, `EN_REPARACION`, `FUERA_DE_SERVICIO`).
    - `historialReparaciones`: Lista de reparaciones con el formato `YYYY-MM-DD: [NombreTaller] Descripción`.
  
- **Métodos**:
    - `calcularCostoReparacion(int horas)`: Método abstracto que calculará el costo de reparación.
    - `iniciarReparacion(String nombreTaller, String descripcion)`: Inicia una reparación y actualiza el historial.
    - `finalizarReparacion()`: Finaliza una reparación y actualiza el historial.
    - `obtenerHistorialReparaciones()`: Retorna el historial de reparaciones.

### 2. **Subclase VehiculoCompacto**

- **Atributos**:
    - `anio`: Año de fabricación del vehículo.
  
- **Métodos**:
    - `calcularCostoReparacion(int horas)`: Calcula el costo de reparación con un descuento del 5% si el vehículo es del año actual.
    - `esVehiculoNuevo()`: Indica si el vehículo fue fabricado en el año actual.

### 3. **Subclase VehiculoSUV**

- **Atributos**:
    - `traccionIntegral`: Indica si el vehículo tiene tracción integral.
  
- **Métodos**:
    - `calcularCostoReparacion(int horas)`: Calcula el costo de reparación con un incremento del 10% si el vehículo tiene tracción integral.
    - `esTraccionIntegral()`: Retorna true si el vehículo tiene tracción integral.

### 4. **Clase Taller**

- **Atributos**:
    - `nombreTaller`: Nombre del taller.
    - `inventarioVehiculos`: Lista de vehículos disponibles en el taller.
    - `vehiculosEnReparacion`: Lista de vehículos actualmente en reparación.
    - `vehiculosFueraDeServicio`: Lista de vehículos fuera de servicio.
  
- **Métodos**:
    - `agregarVehiculo(Vehiculo vehiculo)`: Agrega un nuevo vehículo al inventario del taller.
    - `calcularCostoReparacion(String codigoVehiculo, int horas)`: Calcula el costo de reparación de un vehículo específico.
    - `iniciarReparacion(String codigoVehiculo, String descripcion)`: Inicia la reparación de un vehículo.
    - `finalizarReparacion(String codigoVehiculo)`: Finaliza la reparación de un vehículo.
    - `traerVehiculosDisponibles()`: Retorna una lista de vehículos disponibles.
    - `traerVehiculos(EstadoVehiculo estado)`: Retorna una lista de vehículos según su estado.
    - `obtenerHistorialReparaciones(String codigoVehiculo)`: Retorna el historial de reparaciones de un vehículo.
