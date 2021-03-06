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
import model.Account;
import model.Customer;
import model.Product;

/**
 *
 * @author Markus
 */
@Stateless
@Path("model.customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "FDPM-SERVERPU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Customer entity) {
        super.create(entity);
    }

    @POST
    @Path("ret")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Customer createReturn(Customer entity) {
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Customer entity) {
        super.edit(entity);
    }

    //ADDS
    @PUT
    @Path("{cId}/product/{pId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void addProduct(@PathParam("cId") Long customerId, @PathParam("pId") Long productId) {
        Customer customer = this.find(customerId);
        Product product = getEntityManager().find(Product.class, productId);
        customer.addProduct(product);
        em.persist(product);
    }

    @PUT
    @Path("{cId}/account/{aId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void addAccount(@PathParam("cId") Long customerId, @PathParam("aId") String accountName) {
        Customer customer = this.find(customerId);
        Account account = getEntityManager().find(Account.class, accountName);
        customer.addAccount(account);
        account.addCustomer(customer);
        em.persist(account);
    }

    //DELETES
    @PUT
    @Path("{cId}/dproduct/{pId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteProduct(@PathParam("cId") Long customerId, @PathParam("pId") Long productId) {
        Customer customer = this.find(customerId);
        Product product = getEntityManager().find(Product.class, productId);
        customer.deleteProduct(product);
        em.persist(product);
    }

    @PUT
    @Path("{cId}/daccount/{aId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void deleteAccount(@PathParam("cId") Long customerId, @PathParam("aId") String accountName) {
        Customer customer = this.find(customerId);
        Account account = getEntityManager().find(Account.class, accountName);
        customer.deleteAccount(account);
        em.persist(account);
    }

    @PUT
    @Path("ret/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Customer editReturn(@PathParam("id") Long id, Customer entity) {
        super.edit(entity);
        return entity;
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    //SHOWS
    @GET
    @Path("{cId}/products")
    @Produces({MediaType.APPLICATION_JSON})
    public List showProducts(@PathParam("cId") Long customerId) {
        Customer customer = this.find(customerId);
        return customer.getProducts();
    }

    @GET
    @Path("{cId}/accounts")
    @Produces({MediaType.APPLICATION_JSON})
    public List showAccounts(@PathParam("cId") Long customerId) {
        Customer customer = this.find(customerId);
        return customer.getAccounts();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
