package com.amd.apidio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.amd.apidio.dto.ClienteDTO;
import com.amd.apidio.dto.ClienteNewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amd.apidio.domain.Cliente;
import com.amd.apidio.services.ClienteService;

@RestController
@RequestMapping(value="/apidio/clientes/v1")
public class ClienteResource {
	
	/*O controlador rest acessa a camada servico com essa declaração de dependência*/
	@Autowired
	private ClienteService service;

	/* Aqui está implementado um método para recuperar um cliente do banco de dados */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {		
		/* Quando há uma execeção ela é capturada aqui e tem que mandar um JSON apropriado para a resposta http do recurso */
		/* Foi criada uma classe Handler para capturar a execeção e tratá-la */		
		Cliente obj = service.find(id); /* Aqui chama o metodo find do ClienteService */
		return ResponseEntity.ok().body(obj);
	}

	/* Aqui está implementado um médoto para inserir um novo cliente no banco de dados */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) { /* Tem que validar com @Valid antes de converter */
		/* Antes de chamar o insert tem que converter o objeto DTO para um objeto Entity */
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	/* Aqui está implementado um método para atualizar um cliente no banco de dados, ele é uma mescla dos metodos find e insert */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj= service.update(obj);
		return ResponseEntity.noContent().build(); /* Retorna um conteúdo vazio */
	}
	
	/* Aqui está implementado o método para deletar um cliente do banco de dados */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {		
		service.delete(id);
		return ResponseEntity.noContent().build(); /* Retorna um conteúdo vazio */
	}	
	
	/* Aqui está implementado o método para buscar todos os clientes do banco de dados */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {		
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	/* Aqui está implementado o método para buscar todos os clientes do banco de dados e fazer a paginação*/
	/* url exemplo: http://localhost:8080/apidio/clientes/v1/page?linesPerPage=3&page=1&direction=DESC */
	
	@RequestMapping(value="/page", method=RequestMethod.GET) /* o value foi acrecentado para diferenciar a chamada na url */
	public ResponseEntity<Page<ClienteDTO>> findPage(		/* A anotação @RequestParam foi utilizada para que os parametros sejam opcionais na url que chama a paginação */
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, /* Sugerido 24 que se adequa melhor a layout responsivo */
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) /* defaultValues ASC ou DESC */
	{		
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	

}
