package com.example.L07SpringMVCAnnotationdemo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
public class RequestScopeService {
}
