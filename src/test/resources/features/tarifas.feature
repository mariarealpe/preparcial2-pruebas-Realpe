# language: es
Característica: Cobro y descuentos en el parqueadero EstacionaYa
  Como dueño del parqueadero
  Quiero que el sistema calcule cuánto paga cada vehículo y aplique los descuentos
  Para cobrar lo justo a cada cliente

  Esquema del escenario: Cobro según el tiempo de estadía
    Dado que un <vehiculo> estuvo <minutos> minutos en el parqueadero
    Cuando se calcula la tarifa
    Entonces debe pagar <valor> pesos

    Ejemplos:
      | vehiculo | minutos | valor | situación                                   |
      | carro    | 15      | 0     | dentro de los minutos de gracia             |
      | moto     | 15      | 0     | dentro de los minutos de gracia             |
      | carro    | 16      | 3000  | pasada la gracia paga una hora              |
      | moto     | 16      | 1500  | pasada la gracia paga una hora              |
      | carro    | 61      | 6000  | una hora y un minuto paga dos horas         |
      | moto     | 61      | 3000  | una hora y un minuto paga dos horas         |
      | carro    | 1440    | 25000 | todo el día no supera el tope de carro      |
      | moto     | 1440    | 12000 | todo el día no supera el tope de moto       |

  Escenario: Un cliente frecuente recibe 10 % de descuento
    Dado un cliente frecuente con una cuenta de 3000 pesos
    Cuando se aplica su descuento
    Entonces el valor final es 2700 pesos

  Escenario: Un cliente con convenio recibe 20 % de descuento sobre el tope del carro
    Dado un cliente convenio con una cuenta de 25000 pesos
    Cuando se aplica su descuento
    Entonces el valor final es 20000 pesos
