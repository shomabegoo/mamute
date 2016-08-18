package br.com.caelum.brutal.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

import javax.inject.Inject;

@Controller
public class HelloController {
    @Inject
    private Result result;

    @Get
    public void list() {
        result.include("message", "Hi, Milad!");
    }

}
