package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Messages extends ArrayList<MessageDTO> implements Serializable {

}
