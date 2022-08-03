package com.wilmardeml.springboot.backend.apirest.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no debe ser vacío")
	@Size(min = 4, max = 12, message = "debe contener entre 4 y 12 caracteres")
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "no debe ser vacío")
	private String apellido;

	@NotEmpty(message = "no debe ser vacío")
	@Email(message = "debe ser una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull(message = "no debe ser vacío")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

    private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    @Serial
	private static final long serialVersionUID = 1L;
}
