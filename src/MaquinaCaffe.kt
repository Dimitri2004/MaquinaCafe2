sealed class EstadoCafe {
    object CafeSolo : EstadoCafe()
    data class ConLeche(val cantidadLeche: Int) : EstadoCafe()
    data class ConAzucar(val cantidadAzucar : Int) : EstadoCafe()
    data class conAzucarYLeche(val catidadLeche: Int, val cantidadAzucar: Int) :EstadoCafe()

}

class MaquinaCaffe{
 var estado: EstadoCafe =EstadoCafe.CafeSolo


    fun agregarLeche(cantidad : Int){
        estado = when(estado){
            is EstadoCafe.ConAzucar ->{
                val azucar =(estado as EstadoCafe.ConAzucar).cantidadAzucar
                EstadoCafe.conAzucarYLeche(cantidad,azucar)
            }

            else ->EstadoCafe.ConLeche(cantidad)
        }
    }

    fun agregarAzucar(cucharadas: Int){

        estado = when(estado){
            is EstadoCafe.ConLeche ->{
                val leche =(estado as EstadoCafe.ConLeche).cantidadLeche
            EstadoCafe.conAzucarYLeche(leche, cucharadas)
        }
            else -> EstadoCafe.ConAzucar(cucharadas)
        }
    }

    fun reiniciar(){
        estado=EstadoCafe.CafeSolo
    }
    fun mostrarEstado(){
        when (estado) {
            is EstadoCafe.CafeSolo -> println("Café solo, sin leche ni azúcar.")
            is EstadoCafe.ConLeche -> println("Café con ${ (estado as EstadoCafe.ConLeche).cantidadLeche } ml de leche.")
            is EstadoCafe.ConAzucar -> println("Café con ${ (estado as EstadoCafe.ConAzucar).cantidadAzucar } cucharadas de azúcar.")
            is EstadoCafe.conAzucarYLeche -> {
                val e = estado as EstadoCafe.conAzucarYLeche
                println("Café con ${e.catidadLeche} ml de leche y ${e.cantidadAzucar} cucharadas de azúcar.")
            }

            else -> {}
        }
    }


}
fun main(){
    val maquina =MaquinaCaffe()

    maquina.mostrarEstado() // Café solo
    maquina.agregarLeche(100)
    maquina.mostrarEstado() // Café con leche
    println("Qiere Azucar : true/false")//preguntar si lo quiere con azucar
    val d = readlnOrNull()?.toBooleanStrict() ?:false
    if (d){
        println("Cuanto azucar quieres?:")
        val cantidad= readlnOrNull()?.toIntOrNull() ?:0
        maquina.agregarAzucar(cantidad)
    }
    maquina.mostrarEstado()
    maquina.reiniciar()
}