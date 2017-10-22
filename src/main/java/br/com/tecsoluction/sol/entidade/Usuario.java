package br.com.tecsoluction.sol.entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private long idusuario;

	private String username;
	private String senha;
	private String email;
	private boolean isativo;
	private Set<Role> roles;
	private String foto ;
	

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Usuario() {
		
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public long getId(){
		
		return idusuario;
	}
	
	public void setId(long id){
		
		this.idusuario=id;
	}
	
    @Column(name = "username")
    @NotBlank
	public String getUsername(){
		
		return username;
	}
	
	public void setUsername(String nome){
		
		this.username=nome;
	}
    @Column(name = "senha")
    @NotBlank
	@Length(min = 6, message = "*Your password must have at least 3 characters")
	public String getSenha(){
		
		return senha;
	}
	
	public void setSenha(String senha){
		
		this.senha=senha;
	}
	
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
//	@NotEmpty(message = "*Please provide an email")
	public String getEmail(){
		
		return email;
	}
	
	public void setEmail(String email){
		
		this.email=email;
	}
    @Column(name = "isativo")
	public boolean getIsativo(){
		
		return isativo;
	}
	
	public void setIsativo(boolean valor){
		
		this.isativo=valor;
	}
	
//	@LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore 
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idrole"))
	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }

    
	    
		@Override
		public String toString() {
			return username.toUpperCase();
		}
	
	
	
}
