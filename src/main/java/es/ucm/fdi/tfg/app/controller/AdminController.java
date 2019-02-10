package es.ucm.fdi.tfg.app.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ucm.fdi.tfg.app.vuforia.service.Vuforia;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({ "", "/" })
    public String root(Model model) {
        Vuforia vuforia = new Vuforia("de455b218db45eb8753055d90bf4efdec5345620",
                "35cf4815d61c3bd625390330ad8da5ab071d8193");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = vuforia.getTargets();
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("data", jsonObject.getJSONArray("results"));
        return "admin";
    }

    @GetMapping("/save")
    public String save() {
        return "form";
    }

}