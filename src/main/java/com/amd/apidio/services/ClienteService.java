package com.amd.apidio.services;

import java.util.List;
import java.util.Optional;

import com.amd.apidio.dto.ClienteDTO;
import com.amd.apidio.dto.ClienteNewDTO;
import com.amd.apidio.repositories.ClienteRepository;
import com.amd.apidio.repositories.EnderecoRepository;
import com.amd.apidio.services.exceptions.DataIntegrityException;
import com.amd.apidio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amd.apidio.domain.Cidade;
import com.amd.apidio.domain.Cliente;
import com.amd.apidio.domain.Endereco;
import com.amd.apidio.domain.enums.TipoCliente;

@Service
public class ClienteService {

	/*O serviço acessa a camada repository (acesso a dados) com essa declaração de dependência*/
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {		
		/* Caso o id não exista essa exceção é recebida pela camada de resources REST */		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));		
	}

	/* Esse método é utilizado no CategoriaResource para inserir uma categoria no banco de dados */
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null); /* é necessário informar o id nulo para o metodo save não pensar que é uma atualização e sim uma inserção */
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;	
	}
	
	
	/* Esse método é utilizado no ClienteResource para atualizar uma categoria no banco de dados */
	/* A diferença entre o método insert e o update é o Id. Quando ele é setado null o save insere e quanto ele não é null o save atualiza */
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); /* Se o objeto não existir é criada uma exceção pelo método find */
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	/* Esse método é utilizado no ClienteResource para deletar uma categoria do banco de dados */
	public void delete(Integer id) {
		find(id); /* Se o objeto não existir é criada uma exceção pelo método find */
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			/* Aqui foi criada uma classe personalizada do service para tratar essa exceção */
			throw new DataIntegrityException("Não é possível excluir um cliente que possui referências associadas a ele!");
		}
	}
	
	/* Esse método é utilizado pelo ClienteService para buscar todos os clientes no banco de dados */
	public List<Cliente> findAll() {		
		return repo.findAll();
	}
	
	/* Esse método é utilizado para retornar uma consulta a Clientes paginada */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	/* Esse método converte um objDto para um objeto da entidade Cliente e é chamado do ClienteResource */
	/* A partir de um DTO é construido um objeto Cliente */
	/* É um metodo auxiliar que instancia um Cliente a partir de um DTO */
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getSobrenome(), objDto.getEmail(), null, null);	
	}

	/* Esse método é uma sobrecarga do Cliente fromDTO acima que é utilizado na classe ClienteNewDTO */
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getSobrenome() , objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo())); /* O construtor do Cliente recebe um TipoCliente ai foi necessário converter o parâmetro para TipoCliente que é um inteiro */
		/* Instanciado aqui um objeto persistente monitorado pelo JPA e vai ser associado ao endereco end */
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		/* Aqui o cliente conhece os endereços dele */
		cli.getEnderecos().add(end);
		/* Aqui trata os telefones do cliente para adiciona-los */
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
