import kotlin.math.absoluteValue

sealed class EstadoCafe {
    object CafeSolo : EstadoCafe()
    data class ConLeche(val cantidadLeche: Int) : EstadoCafe()
    data class ConAzucar(val cantidadAzucar : Int) : EstadoCafe()
    data class ConAzucarYLeche(val catidadLeche: Int, val cantidadAzucar: Int) :EstadoCafe()
    data class MesageError(val error:String) : EstadoCafe()
}

/**
 * @param MaquinaCaffe
 *
 */
class MaquinaCaffe{
 var estado: EstadoCafe =EstadoCafe.CafeSolo

    /**
     * @param agregarLeche
     * @return EstadoCafe->Correspondiente cantidad de leche
     */
    fun agregarLeche(cantidad : Int){
        estado = when(estado){
            is EstadoCafe.ConAzucar ->{
                val azucar =(estado as EstadoCafe.ConAzucar).cantidadAzucar
                EstadoCafe.ConAzucarYLeche(cantidad,azucar)
            }

            else ->EstadoCafe.ConLeche(cantidad.absoluteValue)
        }
    }

    /**
     * @param agregarAzucar
     * @return EstadoCafe->Correspondiente a cantidad de azucar
     */
    fun agregarAzucar(cucharadas: Int){

        estado = when(estado){
            is EstadoCafe.ConLeche ->{
                val leche =(estado as EstadoCafe.ConLeche).cantidadLeche
            EstadoCafe.ConAzucarYLeche(leche, cucharadas)
        }
            else -> EstadoCafe.ConAzucar(cucharadas.absoluteValue)
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
            is EstadoCafe.ConAzucarYLeche -> {
                val e = estado as EstadoCafe.ConAzucarYLeche
                println("Café con ${e.catidadLeche} ml de leche y ${e.cantidadAzucar} cucharadas de azúcar.")
            }

            else -> {EstadoCafe.MesageError("Máquina averiada")}
        }
    }


}
fun main(){
    val maquina =MaquinaCaffe()

    maquina.mostrarEstado() // Café solo
    maquina.agregarLeche(5)
    maquina.mostrarEstado() // Café con leche
    maquina.agregarAzucar(6)
    maquina.mostrarEstado()
    maquina.reiniciar()
}