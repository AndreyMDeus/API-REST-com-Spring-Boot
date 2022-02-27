package com.amd.apidio.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amd.apidio.domain.Cidade;
import com.amd.apidio.domain.Cliente;
import com.amd.apidio.domain.Endereco;
import com.amd.apidio.domain.Estado;
import com.amd.apidio.domain.enums.TipoCliente;
import com.amd.apidio.repositories.CidadeRepository;
import com.amd.apidio.repositories.ClienteRepository;
import com.amd.apidio.repositories.EnderecoRepository;
import com.amd.apidio.repositories.EstadoRepository;

@Service
public class DBService {

	/*Essas dependencias permitem que os dados sejam salvos no banco pelo repository no inicio da aplicação*/
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	public CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	public EnderecoRepository enderecoRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		/* Aqui está sendo populado o banco no incio da aplicação */
		
		/* Aqui é populada a tabela de estados */
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		/* Aqui é populada a tabela de cidades */
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		/* Aqui é populada a tabela de clientes */
		Cliente cli1 = new Cliente(null, "Maria Silva", "da Silva", "maria@gmail.com", "36378912377",TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Fulano de Tal", "de Tal", "fulanodetal@gmail.com", "12378999900",TipoCliente.PESSOAFISICA);

		/* Aqui é populada a tabela com os telefones do cliente */
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		cli2.getTelefones().addAll(Arrays.asList("12345678","98765421"));

		/* Aqui é populada a tabela com os endereços do cliente */
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Avenida A1", "1005", "Loja 10", "Centro", "70052652", cli2, c3);

		/* Aqui é mapeado o cliente com seus endereços */
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		/* Aqui é feita a associação do Estado e suas Cidades */
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		/* Aqui salva todos os estados,cidades,clientes e enderecos no banco de dados */
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

	}
}
