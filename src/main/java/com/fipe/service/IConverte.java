package com.fipe.service;

import java.util.List;

public interface IConverte {
    <T> T converteDado(String json, Class<T> classe);

    <T> List<T> converteDadoLista(String json,Class<T> classe);
}
