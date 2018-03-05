/*
 * Copyright (C) 2018 Markus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Color;
import model.Material;
import model.Product;

/**
 *
 * @author Markus
 */
@Stateless
@Path("model.product")
public class ProductFacadeREST extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "FDPM-SERVERPU")
    private EntityManager em;

    public ProductFacadeREST() {
        super(Product.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Product entity) {
        super.create(entity);
    }

    @POST
    @Path("ret")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Product createReturn(Product entity) {
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Product entity) {
        super.edit(entity);
    }

    @PUT
    @Path("ret")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Product editReturn(Product entity) {
        em.merge(entity);
        return entity;
    }

    @PUT
    @Path("{pId}/addcolor/{cId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void addColor(@PathParam("pId") Long productId, @PathParam("cId") Long colorId) {
        Product product = this.find(productId);
        Color color = getEntityManager().find(Color.class, colorId);
        product.addColor(color);
        em.persist(product);
    }

    @PUT
    @Path("{pId}/addmat/{mId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void addMaterial(@PathParam("pId") Long productId, @PathParam("mId") Long materialId) {
        Product product = this.find(productId);
        Material material = getEntityManager().find(Material.class, materialId);
        product.addMaterial(material);
        em.persist(product);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Product find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Product> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
