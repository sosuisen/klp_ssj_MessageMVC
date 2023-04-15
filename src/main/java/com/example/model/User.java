package com.example.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@lombok.Data
@SessionScoped
public class User implements Serializable {
	private String name;
}

