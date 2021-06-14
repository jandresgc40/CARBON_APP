package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GuiaLista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_lista);
    }

    public void abrirConsejo(Consejo consejo) {

        String titulo;
        String texto;
        int imagen;

        titulo = consejo.getTitulo();
        texto = consejo.getTexto();
        imagen = consejo.getImagen();

        Intent intent = new Intent(GuiaLista.this, PantallaConsejo.class);
        intent.putExtra("titulo", titulo);
        intent.putExtra("texto", texto);
        intent.putExtra("imagen", imagen);

        startActivity(intent);

    }

    public void boton1(View v) {

        Consejo consejo = new Consejo("¿Qué es la huella de carbono?", "La huella de carbono nace como una medida de cuantificar y generar un indicador del impacto que una actividad o proceso tiene sobre el cambio climático, más allá de los grandes emisores.\n" +
                "\n" +
                "La huella de carbono se define como el conjunto de emisiones de gases de efecto invernadero producidas, directa o indirectamente, por personas, organizaciones, productos, eventos o regiones geográficas, en términos de CO2 equivalentes, y sirve como una útil herramienta de gestión para conocer las conductas o acciones que están contribuyendo a aumentar nuestras emisiones, cómo podemos mejorarlas y realizar un uso más eficiente de los recursos.\n\n" +
                "En esta app vas a poder calcular tu huella personal. El número que te indica la pantalla de inicio son tus toneladas anuales. Partimos de 7 toneladas y realizando los diversos tests que te proponemos sobre el hogar, la vivienda y el transporte podremos ser capaces de hacer un cálculo aproximado de tu huella de carbono. Esta guía te va a ayudar a comprender los diferentes parámetros que condicionan tu huella.", R.drawable.intro);
        abrirConsejo(consejo);

    }

    public void boton2(View v) {

        Consejo consejo = new Consejo("Huella en el hogar","Como ya hemos dicho en otras ocasiones la rehabilitación energética de los edificios es una de las medidas más eficaces para reducir su huella de carbono.\n" +
                "\n" +
                "En este sentido habrá que prestar especial atención a los materiales utilizados en su construcción pero también en las futuras reformas que se hagan.\n" +
                "\n" +
                "Esto cobra especial importancia en las acciones acometidas en el aislamiento térmico del edificio afectando tanto a la envolvente como a los cerramientos de ventanas y puertas.\n" +
                "\n" +
                "Por ejemplo, a través de la utilización de materiales SATE que promueven la eliminación de puentes térmicos, reducen el riesgo de condensaciones y son impermeables al agua de lluvia y al vapor de agua.\n" +
                "\n" +
                "O mejorando el confort interior a través de filtros de aire de alta eficiencia mejoran la calidad del aire a través de la filtración de tóxicos favoreciendo la buena salud respiratoria de los habitantes. Por otro lado, mantienen la temperatura y la humedad a niveles estables.\n" +
                "\n" +
                "Otro gran punto a tener en cuenta es la eficiencia en el uso de la energía, sobre todo en la climatización que puede administrarse a través de la instalación de dispositivos de medición y análisis de consumo además de introducir elementos domóticos SMART Home que ayudan a su control.\n" +
                "\n" +
                "Otra buena medida es la colocación paneles solares que aportaría energía de origen renovable reduciendo en gran medida la huella de carbono del conjunto del edificio, además de suponer un gran ahorro económico en el gasto eléctrico proveniente de la red eléctrica general.",  R.drawable.edificio);
        abrirConsejo(consejo);

    }

    public void boton3(View v) {

        Consejo consejo = new Consejo("Un vehículo eléctrico ayuda, pero no es necesario", "Parece que el vehículo eléctrico es la solución medioambiental definitiva pero hay que realizar ciertos matices. Es verdad que no emite gases de efecto invernadero de la forma directa como lo haría un coche de combustión pero has de saber que en la fabricación de ese coche eléctrico y la energía eléctrica que utiliza para moverse ha emitido una huella de carbono considerable.\n\n"
                + "¿Indica esto que no son tan buenos? Para nada, realmente ayudan bastante pero hay que amortizarlos y tener en cuenta de dónde se obtiene la energía que los alimenta, ahora bien, la mayoría de la pobración hoy en día utiliza coches de combustión y realmente se puede optimizar su uso para tener una menor huella de carbono.\n\n" +
                "Elegir vehículos según su calificación energética.\n" +
                "Cuanto más eficiente sea un vehículo en el consumo de combustible, además de reducir costos por este concepto, también se reducen las emisiones de CO2.\n\n" +
                "Considerar el uso de vehículo que utilizan combustibles alternativos\n" +
                "Los vehículos de combustión a gas generan un 25 % menos emisiones que los vehículos a gasolina, y un 9 % menos que los diésel.\n" +
                "\n" +
                "Usar vehículos con la dimensión apropiada para las necesidades de transporte.\n" +
                "Cuanto más grande es el vehículo, mayor consumo tiene. Las dimensiones de este deben elegirse en función de la ruta y la cantidad de mercancía a transportar.\n" +
                "\n" +
                "Planificar las rutas.\n" +
                "Evaluar los recorridos para escoger las rutas más cortas y las que ofrezcan menos interrupciones.\n" +
                "\n" +
                "A veces, una ruta más larga resulta más conveniente. Optimizar el tiempo que el vehículo permanecerá en las vías ayuda a reducir la huella de carbono por consumo de combustible.\n" +
                "\n" +
                "Incluir en el programa de formación cursos de conducción eficiente.\n" +
                "Formar y concienciar a los conductores acerca de los principios de la conducción eficiente, puede contribuir a reducir el consumo de combustible en cerca de un 15%, lo que representa ahorros en los costos de operación y una reducción considerable de las emisiones de CO2.\n" +
                "\n" +
                "Mantenimiento preventivo y de rutina.\n" +
                "El mantenimiento preventivo programado y la revisión periódica de la presión de aire de los neumáticos es indispensable para garantizar un consumo eficiente de combustible.", R.drawable.ev);
        abrirConsejo(consejo);

    }

    public void boton4(View v) {

        Consejo consejo = new Consejo("¿Por qué la dieta vegana contribuye a la reducción?", "Según un estudio del científico inglés Peter Scarborourgh de la Universidad de Oxford y publicado en la revista Science, el 25% de las emisiones de gases de efecto invernadero derivan de la industria de la alimentación, de la cual el 58% son de productos de origen animal y de ese porcentaje… la mitad se le atribuye a la carne de res, ternera o cordero.\n\nPor lo que el Profr. Scarborough sugiere que si cambiáramos nuestra dieta a una vegetariana incluyendo frutos de temporada, podríamos reducir hasta en dos terceras partes la huella de carbono en los alimentos que consumimos.\n\n" +
                "La recomendación que sugiere el artículo llamado “La enorme huella ecológica del consumo de carne” publicado en la revista National Geographic plantea una reducción en el consumo de carne y sustituirlos con alimentos derivados de los animales como el huevo, además de frutas y verduras de temporada. Una vez que se siga la recomendación, se asegura que nuestra alimentación influirá desde ahorrar agua hasta reducir la contaminación y evitar la pérdida de bosques.", R.drawable.vegana);
        abrirConsejo(consejo);

    }

    public void boton5(View v) {

        Consejo consejo = new Consejo("¿Productos Eco o Bio?", "Este es el nombre que se le da a los alimentos que se han producido utilizando métodos tradicionales,que rechazan el uso fertilizantes y pesticidas sintéticos y otras técnicas modernas para producir más.\n" +
                "\n" +
                "Pero una cosa es cierta: su uso cuida el suelo, al no explotarlo tan intensivamente no rebaja tanto su capacidad para capturar carbono, a la vez que con las técnicas tradicionales se ahorra la energía de producir los fertilizantes y pesticidas.\n" +
                "\n" +
                "Esto hace que tomar productos ecológicos pueda suponer una reducción de unas 0.5 toneladas…\n" +
                "\n" +
                "Pero, los autores advierten que existen casos en los que los productos ecológicos han empeorado la huella. Y es que en comparación con los convencionales, los productos ecológicos suelen necesitar de más recursos para alcanzar la misma producción (ya que no cuentan con las ventajas de la modernidad), lo que hace que a veces no compense.\n" +
                "Y dado que la población mundial está creciendo y su demanda de comida también, estar talando más árboles para plantar productos eco no parece una solución muy sostenible.\n" +
                "\n" +
                "Cómo la agricultura ecológica podría utilizarse correctamente como un arma contra el cambio climático todavía es algo que se debate a nivel científico, así que cautela con ella.", R.drawable.ecobio);
        abrirConsejo(consejo);

    }

    public void boton6(View v) {

        Consejo consejo = new Consejo("¿Por qué mejor un calefactor eléctrico?", "Pasar de calentar tu casa con gas fósil un sistema que tire de la energía de la red, como un radiador eléctrico, es mucho mejor.\n" +
                "\n" +
                "Sin duda suele ser más caro para el bolsillo, pero barato en términos de huella. Si esta electricidad fuera 100% de origen renovable, puede reducir la huella 0.7 toneladas de media, lo que me lleva a lo siguiente.\n" +
                "\n" +
                "Si te cambias a un proveedor de electricidad de energía totalmente libre de emisiones, la reducción de tu huella puede ser entre 0.3 y 2.5 toneladas.\n" +
                "\n" +
                "Esto puede ser terriblemente complicado de conseguir, hasta incluso engañoso, ya que existen pequeños proveedores que te venden electricidad “ecoverde” pero luego cuando miras la letra pequeña resulta que no es así.\n" +
                "\n" +
                "Si os planteáis esta opción, por favor, chequead todo con lupa, que no os la cuelen. Pero me queda la más evidente: producir tu propia energía renovable.\n" +
                "\n" +
                "Si tenéis la suerte de poder instalar placas solares tu huella se beneficia entre 4.8 y 0.1 toneladas.\n" +
                "\n" +
                "Depende mucho del coste de los materiales y su fabricación, la orientación de la casa, la localización... Al fin y al cabo no se va recoger la misma energía en Barcelona que en Londres.", R.drawable.calorhogar);
        abrirConsejo(consejo);

    }

    public void boton7(View v) {

        Consejo consejo = new Consejo("Si estás leyendo esto, vas bien.", "En este apartado queremos agradecerte el haber descargado nuestra APP, solo con ese gesto ya se demuestra estar concienciado y es un primer paso para ayudar entre todos a frenar el calentamiento del planeta.\n\n"
                + "No es una tarea fácil, hay pasos pequeños que puede realizar todo el mundo. Lo importante es ser constante y no parar. Este planeta es lo único que tenemos y debemos cuidarlo con los medios que tenemos. Esta applicación es una calculadora rápida, sencilla y fácil de utilizar,lo que puede hacer que resulte imprecisa.\n\n"
                +"Se puede realizar cálculos por KWh consumidos en el hogar, calculando la eficiencia energética, incluso por marca que consumes. No queremos llegar a ese extremo, si necesitas un resultado más preciso puedes encontrar calculadoras de huella de carbono en la web! \n\n" +
                "Gracias a tu ayuda y a las personas concienciadas como tu conseguiremos mejorar la salud del planeta!\n\n" +
                "Fuente de la ilustración - viivus.tumblr.com", R.drawable.animacion_imagen);
        abrirConsejo(consejo);

    }

}