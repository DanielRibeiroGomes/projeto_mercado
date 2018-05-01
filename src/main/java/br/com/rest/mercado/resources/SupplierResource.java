package br.com.rest.mercado.resources;

import br.com.rest.mercado.dto.SupplierDTO;
import br.com.rest.mercado.models.Supplier;
import br.com.rest.mercado.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierResource {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Supplier supplier = supplierService.find(id);

        return ResponseEntity.ok().body(supplier);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody SupplierDTO supplierDTO){
        Supplier supplier = supplierService.fromDTO(supplierDTO);
        supplier = supplierService.insert(supplier);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(supplier.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody SupplierDTO supplierDTO, @PathVariable Integer id){
        Supplier supplier = supplierService.fromDTO(supplierDTO);
        supplier.setId(id);
        supplier = supplierService.update(supplier);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        supplierService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Page<SupplierDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction){

        Page<Supplier> list = supplierService.findPage(page, linesPerPage, orderBy, direction);
        Page<SupplierDTO> listDTO = list.map(obj -> new SupplierDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}
