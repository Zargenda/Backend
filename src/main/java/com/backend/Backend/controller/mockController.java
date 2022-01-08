package com.backend.Backend.controller;

import com.backend.Backend.Service.HorarioService;
import com.backend.Backend.model.HorarioAsignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mock")
public class mockController {

        @PostMapping("/upload")
        public String uploadMock(@RequestParam("file") MultipartFile file){
            return "EXITO";
        }
        @GetMapping("/getPlanes")
        public  String[] getPlanes(){
            String[] planes = {"informatica","tumama","mamawebo","Tecnologia"};
            return planes;
        }
        @GetMapping("/getInfoPlan")
        public Map<Integer,String[]> infoPlan(@RequestParam("plan") String plan ){
            Map<Integer, String[]> result = new HashMap<Integer,String[]>();
            String[] semestre = {"S1","S2","S"};
            String[] curso = {"1","2","3","4"};
            String[] grupo = {"1","2","3","4","5"};
            result.put(1,semestre);
            result.put(2,curso);
            result.put(3,grupo);
            return result;
        }

    @GetMapping("/getAsignaturasPlan")
    public String[] getAsignaturas(@RequestParam("plan") String plan ){
        String[] result = {"Bailar","Canto","Yone","Si AMIGO"};
        return result;
    }



}
