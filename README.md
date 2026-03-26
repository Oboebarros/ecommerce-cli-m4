# 🎵 OboeMarket CLI - Sistema de E-commerce para Músicos

Este proyecto es una aplicación de consola en **Java** (Módulo 4) que gestiona
una tienda de accesorios para oboe con un motor de descuentos dinámico.

**🔗 Repositorio GitHub:** [https://github.com/Oboebarros/ecommerce-cli-m4](https://github.com/Oboebarros/ecommerce-cli-m4)

## 🚀 Requisitos e Instalación

1. Tener instalado **Java JDK 21** y **Maven**.
2. Clonar el repositorio:
```bash
   git clone https://github.com/Oboebarros/ecommerce-cli-m4.git
```
3. Entrar a la carpeta:
```bash
   cd ecommerce-cli-m4
```
4. Compilar y ejecutar:
```bash
   mvn exec:java "-Dexec.mainClass=Main"
```

## 📋 Menús

### Menú Principal
- 1) Admin
- 2) Usuario
- 0) Salir

### Menú Admin
- 1) Listar productos
- 2) Buscar producto
- 3) Crear producto
- 4) Editar producto
- 5) Eliminar producto
- 0) Volver

### Menú Usuario
- 1) Listar productos
- 2) Buscar producto
- 3) Agregar al carrito
- 4) Quitar del carrito
- 5) Ver carrito
- 6) Ver descuentos activos
- 7) Confirmar compra
- 0) Volver

## 🛒 Ejemplo de compra

1. Entrar como **Usuario**
2. Opción 3 → Agregar al carrito: ID 9 (Kit para raspar) cantidad 3
3. Opción 7 → Confirmar compra:
```
===== 💰 DETALLE DE DESCUENTOS =====
✅ Descuento por monto: 10% si el total supera $50.000 → -$10.800
✅ Descuento por categoría: 15% si el carrito tiene productos de 'Kits' → -$16.200

===== ✅ ORDEN #1 CONFIRMADA =====
Kit para raspar     x3    $108.000
----------------------------------
TOTAL BASE:         $108.000
DESCUENTO:          -$27.000
----------------------------------
TOTAL FINAL:        $81.000
==================================
```

## 🏷️ Descuentos automáticos
- **DescuentoPorMonto:** 10% si el total supera $50.000
- **DescuentoPorCategoria:** 15% si el carrito tiene productos de la categoría Kits

## 🧪 Tests
- Test 1: Total del carrito calculado correctamente
- Test 2: Excepción por cantidad inválida
- Test 3: Descuento por monto aplicado correctamente
- Test 4: Descuento por categoría aplicado correctamente