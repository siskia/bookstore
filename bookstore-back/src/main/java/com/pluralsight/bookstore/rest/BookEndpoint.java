package com.pluralsight.bookstore.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;

import javax.ws.rs.core.MediaType;


@Path("/books")
public class BookEndpoint {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private BookRepository bookRepository;

    // ======================================
    // =          Business methods          =
    // ======================================

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.find(id);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.size() == 0)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok(books).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countBooks() {
        Long nbOfBooks = bookRepository.countAll();

        if (nbOfBooks == 0)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok(nbOfBooks).build();
    }
    
}
