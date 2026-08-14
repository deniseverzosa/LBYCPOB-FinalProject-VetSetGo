package com.petpulse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonDataHandler<T> implements DataHandler<T> {

    private final ObjectMapper mapper = new ObjectMapper();

