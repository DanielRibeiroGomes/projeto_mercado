package br.com.rest.mercado.dto;

import br.com.rest.mercado.models.Supplier;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class SupplierDTO implements Serializable {

    private static final long serialVersionUID = 1;

    private Integer id;

    @NotEmpty(message = "O campo não pode ser vazio")
    @Length(min = 3, max = 60, message = "O campo deve ter entre 3 e 60 caracteres")
    private String nome;
    private String cnpj;

    public SupplierDTO() {
    }

    public SupplierDTO(Supplier supplier) {
        id = supplier.getId();
        nome = supplier.getNome();
        cnpj = supplier.getCnpj();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
