package com.deador.restapp.service;

import com.deador.restapp.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Аннотация @Service говорит спрингу, что данный класс является сервисом.
 * Это специальный тип классов, в котором реализуется некоторая бизнес логика приложения.
 */

@Service
public class ClientServiceImpl implements ClientService {
    //--- Хранилище клиентов ---
    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();
    //--- Генерация id клиента ---
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Client client) {
        final int clientID = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(clientID);
        CLIENT_REPOSITORY_MAP.put(clientID, client);
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public Client read(int id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            client.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }
}
