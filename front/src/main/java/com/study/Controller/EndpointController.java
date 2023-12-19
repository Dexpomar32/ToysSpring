package com.study.Controller;

import com.study.Model.EndpointDetail;
import com.study.Model.EndpointDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/endpoints")
public class EndpointController {
    @GetMapping
    public List<EndpointDetails> getEndpoints() {
        EndpointDetails category = getCategoryInfo();
        EndpointDetails toy = getToyInfo();
        EndpointDetails doll = getDollInfo();
        EndpointDetails sale = getSaleInfo();
        EndpointDetails charts = getChartsInfo();

        return List.of(category, toy, doll, sale, charts);
    }

    private static EndpointDetails getSaleInfo() {
        EndpointDetails sale = new EndpointDetails();
        sale.setClassName("Sale");

        List<EndpointDetail> navigation = List.of(
                new EndpointDetail("GET_ALL", "/sale/getAll", "GET", List.of(), List.of()),
                new EndpointDetail("GET_ONE", "/sale/getOne", "GET", List.of("Cod"), List.of("cod")),
                new EndpointDetail("CREATE", "/sale/create", "POST", List.of("Cod", "Toy",
                        "Sale Date", "Quantity"), List.of("cod", "Toy", "saleDate", "quantity")),
                new EndpointDetail("UPDATE", "/sale/update", "POST", List.of("Cod", "Toy",
                        "Sale Date", "Quantity"), List.of("cod", "Toy", "saleDate", "quantity")),
                new EndpointDetail("DELETE", "/sale/delete", "DELETE", List.of("Cod"), List.of("cod")),
                new EndpointDetail("SALES", "/sale/sales", "GET", List.of("Date"), List.of("date")),
                new EndpointDetail("SELL_TOY", "/sale/sell", "POST", List.of("Cod", "Number of toys"), List.of("cod", "quantity"))
        );

        sale.setNavigation(navigation);
        return sale;
    }

    private static EndpointDetails getDollInfo() {
        EndpointDetails doll = new EndpointDetails();
        doll.setClassName("Doll");

        List<EndpointDetail> navigation = List.of(
                new EndpointDetail("GET_ALL", "/doll/getAll", "GET", List.of(), List.of()),
                new EndpointDetail("GET_ONE", "/doll/getOne", "GET", List.of("Cod"), List.of("cod")),
                new EndpointDetail("CREATE", "/doll/create", "POST", List.of("Cod", "Toy", "Material", "Height"),
                        List.of("cod", "Toy", "material", "height")),
                new EndpointDetail("UPDATE", "/doll/update", "POST", List.of("Cod", "Toy", "Material", "Height"),
                        List.of("cod", "Toy", "material", "height")),
                new EndpointDetail("DELETE", "/doll/delete", "DELETE", List.of("Cod"), List.of("cod")),
                new EndpointDetail("ASCENDING", "/doll/ascending", "GET", List.of(), List.of())
        );

        doll.setNavigation(navigation);
        return doll;
    }

    private static EndpointDetails getCategoryInfo() {
        EndpointDetails category = new EndpointDetails();
        category.setClassName("Category");

        List<EndpointDetail> navigation = List.of(
                new EndpointDetail("GET_ALL", "/category/getAll", "GET", List.of(), List.of()),
                new EndpointDetail("GET_ONE", "/category/getOne", "GET", List.of("Cod"), List.of("cod")),
                new EndpointDetail("CREATE", "/category/create", "POST", List.of("Cod", "Category name"),
                        List.of("cod", "name")),
                new EndpointDetail("UPDATE", "/category/update", "POST", List.of("Cod", "Category name"),
                        List.of("cod", "name")),
                new EndpointDetail("DELETE", "/category/delete", "DELETE", List.of("Cod"), List.of("cod"))
        );

        category.setNavigation(navigation);
        return category;
    }

    private static EndpointDetails getToyInfo() {
        EndpointDetails toy = new EndpointDetails();
        toy.setClassName("Toy");

        List<EndpointDetail> navigation = List.of(
                new EndpointDetail("GET_ALL", "/toy/getAll", "GET", List.of(), List.of()),
                new EndpointDetail("GET_ONE", "/toy/getOne", "GET", List.of("Cod"), List.of("cod")),
                new EndpointDetail("CREATE", "/toy/create", "POST", List.of("Cod", "Toy name", "Price",
                        "Quantity", "Country", "Minim age", "Category"), List.of("cod", "name", "price", "quantity",
                        "country", "minimAge", "category")),
                new EndpointDetail("UPDATE", "/toy/update", "POST", List.of("Cod", "Toy name", "Price",
                        "Quantity", "Country", "Minim age", "Category"), List.of("cod", "name", "price", "quantity",
                        "country", "minimAge", "category")),
                new EndpointDetail("DELETE", "/toy/delete", "DELETE", List.of("Cod"), List.of("cod")),
                new EndpointDetail("EXCLUDE", "/toy/exclude", "POST", List.of(), List.of()),
                new EndpointDetail("EXPENSIVE_CHEAP", "/toy/expensiveCheap", "GET", List.of(), List.of()),
                new EndpointDetail("AVG_PRICE", "/toy/avgPrice", "GET", List.of("Country"), List.of("country")),
                new EndpointDetail("INSERT_MOLDOVA", "/toy/insertMoldova", "POST", List.of(), List.of()),
                new EndpointDetail("FILTERS", "/toy/filters", "GET", List.of("Max Price", "Min Age", "Max Age"),
                        List.of("maxPrice", "minimAge", "maxAge")),
                new EndpointDetail("POPULAR", "/toy/popular", "GET", List.of(), List.of())
        );

        toy.setNavigation(navigation);
        return toy;
    }

    private static EndpointDetails getChartsInfo() {
        EndpointDetails charts = new EndpointDetails();
        charts.setClassName("Charts");

        List<EndpointDetail> navigation = List.of(
                new EndpointDetail("COUNTRIES", "/charts/countries", "GET", List.of(), List.of()),
                new EndpointDetail("CATEGORIES", "/charts/categories", "GET", List.of(), List.of()),
                new EndpointDetail("AGE_TOY", "/charts/ageToy", "GET", List.of(), List.of()),
                new EndpointDetail("DATE_SALES", "/charts/dateSales", "GET", List.of(), List.of()),
                new EndpointDetail("DOLL_MATERIAL", "/charts/dollMaterial", "GET", List.of(), List.of())
        );

        charts.setNavigation(navigation);
        return charts;
    }
}