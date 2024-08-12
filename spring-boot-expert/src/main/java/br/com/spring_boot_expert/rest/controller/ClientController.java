package br.com.spring_boot_expert.rest.controller;

import br.com.spring_boot_expert.domain.Client;
import br.com.spring_boot_expert.repositories.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client Controller", description = "Clients API")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find client", description = "Find a client by id.")
    public Client findClientById(@PathVariable("id") Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save client", description = "Save a new client.")
    public Client save(@RequestBody @Valid Client client) {
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete client", description = "Delete a client by id.")
    public void deleteById(@PathVariable Integer id) {
        clientRepository.findById(id)
                .map(client -> {
                    clientRepository.deleteById(id);
                    return client;}
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update client", description = "Update a client information by id.")
    public void update(@PathVariable Integer id, @RequestBody @Valid Client newClient) {
        clientRepository.findById(id).map(client -> {
            client.setName(newClient.getName());
            client.setCpf(newClient.getCpf());
            clientRepository.save(client);
            return client;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    @Operation(summary = "Find all clients", description = "Find all clients")
    public List<Client> find(Client client) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Client> example = Example.of(client, matcher);
        return clientRepository.findAll(example);
    }

}
