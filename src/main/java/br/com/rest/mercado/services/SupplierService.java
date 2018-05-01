package br.com.rest.mercado.services;

import br.com.rest.mercado.dto.SupplierDTO;
import br.com.rest.mercado.models.Supplier;
import br.com.rest.mercado.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {

   @Autowired
   private SupplierRepository supplierRepository;

   public Supplier find(Integer id){
       Optional<Supplier> supplier = supplierRepository.findById(id);

       return supplier.orElse(null);
   }

   public Supplier insert(Supplier supplier){
       supplier.setId(null);

       return supplierRepository.save(supplier);
   }

   public Supplier update(Supplier supplier){
       Supplier obj = find(supplier.getId());
       updateData(obj, supplier);

       return  supplierRepository.save(obj);
   }

   public void delete(Integer id){
       find(id);

       supplierRepository.deleteById(id);
   }

   public Page<Supplier> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
       PageRequest pageRequest = PageRequest.of(page, linesPerPage,Sort.Direction.valueOf(direction), orderBy);

       return supplierRepository.findAll(pageRequest);
   }

    public Supplier fromDTO(SupplierDTO objDTO){
        return new Supplier(objDTO.getId(), objDTO.getNome(), objDTO.getCnpj());
    }

    private void updateData(Supplier newobj, Supplier obj){
        newobj.setNome(obj.getNome());
    }
}
