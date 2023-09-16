package com.example.experimentoapi01.Controller;

import com.azure.storage.queue.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@RequestMapping("/OrdenadoBurbuja")
public class OrdenadoBurbujaController {

    @Autowired
    private Environment environment;
    @GetMapping("/{ordenar}")
    public ResponseEntity<String> ordenar(@PathVariable Integer ordenar) {
        // inicialización de variables
        int  arregloOrdenado[] = new int[ordenar], temp;
        String arregloString = "", arregloOrdenadoString = "";
        Random rand = new Random();
        Boolean ordenado = false;

        // llenado del arreglo numero aleatorios
        for (int i = 0; i < ordenar; i++) {
            arregloOrdenado[i] = rand.nextInt(ordenar) + 1;
            if (i == ordenar - 1) {
                arregloString += arregloOrdenado[i];
            }
            else {
                arregloString += arregloOrdenado[i] + ",";
            }
        }

        //Ordenado Burbuja
        while (!ordenado) {
            ordenado = true;
            for (int i = 0; i < ordenar - 1; i++) {
                if (arregloOrdenado[i] > arregloOrdenado[i + 1]) {
                    temp = arregloOrdenado[i];
                    arregloOrdenado[i] = arregloOrdenado[i + 1];
                    arregloOrdenado[i + 1] = temp;
                    ordenado = false;
                }
            }
        }

        //retorno de arreglo ordenado
        for (int i = 0; i < ordenar; i++) {
            if (i == ordenar - 1) {
                arregloOrdenadoString += arregloOrdenado[i];
            }
            else {
                arregloOrdenadoString += arregloOrdenado[i] + ",";
            }
        }

        String mensajeJson = "{\n    \"arreglo_origonal\": {" + arregloString+"}, \n    \"arreglo_ordenado:\": {" + arregloOrdenadoString + "}\n}";

        EnviarColaDeMensajes(mensajeJson);

        return new ResponseEntity<>(mensajeJson, HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> pingPong() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

    void EnviarColaDeMensajes(String mensajeJson) {
        String conection = environment.getProperty("AZURE_STORAGE_CONNECTION_STRING");
        QueueClient queueClient = new QueueClientBuilder()
                .connectionString(conection)
                .queueName("cola-de-mensajes")
                .buildClient();

        queueClient.sendMessage(mensajeJson);
    }
}
