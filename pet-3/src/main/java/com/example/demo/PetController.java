package com.example.demo;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	PetMapper petMapper;
	
	@GetMapping
	public List<Pet> findAll(){
		return petMapper.findAll();
	}
	//登録されているPetリストを１件表示
	@GetMapping("/{id}")
	public PetResponse findById(@PathVariable long id) {
		// DBからidをキーにデータを取得
		Pet pet = petMapper.findById(id);

		// Responseにデータをコピーしてreturn
		PetResponse petResponse = new PetResponse();
		BeanUtils.copyProperties(pet, petResponse);
		return petResponse;
	}
	
	//追加
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse doPost(@RequestBody PetRequest petRequest){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petRequest, pet);

        long ret = petMapper.insert(pet);

        PetResponse petResponse = new PetResponse();
        BeanUtils.copyProperties(pet, petResponse);

        return petResponse;
    }

	//更新
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetResponse doPut(@PathVariable int id, @RequestBody PetRequest petRequest){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petRequest, pet);
        pet.setId(id);
        petMapper.update(pet);

        PetResponse petResponse = new PetResponse();
        BeanUtils.copyProperties(pet, petResponse);

        return petResponse;
    }
    
	//削除
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void doDelete(@PathVariable int id){
        boolean ret = petMapper.delete(id);
    }


//
//	@GetMapping
//	public List<Pet> allPet(){
//		ArrayList<Pet> arrayList = new ArrayList<Pet>();
//		arrayList.add(new Pet(1,"umi"));
//		arrayList.add(new Pet(2,"umi"));
//		arrayList.add(new Pet(3,"umi"));
//		return arrayList;
//	}
	
//	@GetMapping
//	public List<Pet> getPets() {
//		List<Pet> petList = petMapper.findAll();
//		return petList;
//	}
	

//	//登録されているPetリストを表示
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public List<PetResponse> getPets() {
//		List<PetResponse> petResponseList = new ArrayList<>();
//
//		List<Pet> petList = petMapper.findAll();
//
//		petList.forEach(pet -> {
//			PetResponse petResponse = new PetResponse();
//			BeanUtils.copyProperties(pet, petResponse);
//			petResponseList.add(petResponse);
//		});
//
//		return petResponseList;
//	}

//	//登録されているPetリストを１件表示
//	@GetMapping("/{id}")
//	public PetResponse findById(@PathVariable long id) {
//		// DBからidをキーにデータを取得
//		Pet pet = petMapper.findById(id);
//
//		// Responseにデータをコピーしてreturn
//		PetResponse petResponse = new PetResponse();
//		BeanUtils.copyProperties(pet, petResponse);
//		return petResponse;
//	}

	/*//登録されているPetリストを表示
	@GetMapping
	public List<Pet> getAllpet() {
		return petMapper.findAll();

	}

	//登録されているPetリストを１件表示
	@GetMapping("/{id}")
	public Pet getPet(@PathVariable("id") long id) {
		return petMapper.findById(id);

	}*/
}
