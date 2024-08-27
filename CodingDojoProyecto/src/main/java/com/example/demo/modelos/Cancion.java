package com.example.demo.modelos;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String titulo;

    @NotNull
    private String genero;

    @NotNull
    @Size(min = 5)
    private String letra;
    
    @Transient
    private String letraOriginal;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "cancion", cascade=CascadeType.ALL)
    private List<Colaborador> colaboradores;

    public Cancion() {}

    public Cancion(Long id, String titulo, String genero, String letra, Date createdAt, Date updatedAt, Usuario usuario, List<Colaborador> colaboradores) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.letra = letra;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.usuario = usuario;
        this.colaboradores = colaboradores;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public String getLetraOriginal() {
		return letraOriginal;
	}

	public void setLetraOriginal(String letraOriginal) {
		this.letraOriginal = letraOriginal;
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", letra=" + letra
				+ ", letraOriginal=" + letraOriginal + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", usuario=" + usuario + ", colaboradores=" + colaboradores + "]";
	}
	
	
	

}