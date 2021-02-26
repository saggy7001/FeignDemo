import Client.APIClient;
import POJO.Category;
import POJO.Pet;
import POJO.Tag;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestCases {

    @Test
    public void GetPet()
    {
        Pet resp = APIClient.GetClient().petService.GetPet(1);
        System.out.println(resp);
    }

    @Test
    public void CreatePet()
    {
        Pet newPet = new Pet(199, new Category(12, "wild"),
                "dog", Arrays.asList("google.com", "twitter.com"),
                Arrays.asList(new Tag(1,"A"), new Tag(2,"B")), "sold");

        Pet pet = APIClient.GetClient().petService.CreatePet(newPet);
        System.out.println(pet.id);
    }

    @Test
    public void TestAuth()
    {
        String resp = APIClient.GetClient().postmanService.TestAuth();
        System.out.println(resp);
    }
}
