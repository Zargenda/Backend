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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
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
        public Map<String,String[]> infoPlan(@RequestParam("plan") String plan ){
            Map<String, String[]> result = new HashMap<String,String[]>();
            String[] semestre = {"S1","S2","S"};
            String[] curso = {"1","2","3","4"};
            String[] grupo = {"1","2","3","4","5"};
            result.put("Semester",semestre);
            result.put("Grade",curso);
            result.put("Group",grupo);
            return result;
        }

    @GetMapping("/getAsignaturasPlan")
    public String[] getAsignaturas(@RequestParam("plan") String plan ){
        String[] result = {"Bailar","Canto","Yone","Si AMIGO"};
        return result;
    }



}
