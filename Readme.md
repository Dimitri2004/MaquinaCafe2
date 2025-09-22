
```mermaid
---
title: Maquina Café
---
stateDiagram
    State "Estado de cafe con leche" as State2
    State "Estado de cafe con azucar" as State3
    State "Estado de cafe solo" as State1
    State "Estado de cafe con azucar y leche" as State4


State1 --> State2:Accion

State2 --> State3:Accion


State3 -->State2:Accion terminada

State3-->State4:Accion



    
```