package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class InserirContasSteps {
    private WebDriver driver;
    @Dado("^que estou acessando a aplicação$")
    public void que_estou_acessando_a_aplicação() {
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
    }

    @Quando("informo o usuário {string}")
    public void informo_o_usuário(String string) {
        driver.findElement(By.id("email")).sendKeys(string);
    }

    @Quando("a senha {string}")
    public void a_senha(String string) {
        driver.findElement(By.id("senha")).sendKeys(string);
    }

    @Quando("seleciono entrar")
    public void seleciono_entrar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("visualizo a página inicial")
    public void visualizo_a_página_inicial() {
      String texto =  driver.findElement(By.xpath("//div[@class=\"alert alert-success\"]")).getText();
        Assert.assertEquals("Bem vindo, a!", texto);
    }

    @Quando("seleciono Contas")
    public void seleciono_contas() {
        driver.findElement(By.linkText("Contas")).click();
    }

    @Quando("seleciono Adicionar")
    public void seleciono_adicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("informo a conta {string}")
    public void informo_a_conta(String string) {
        driver.findElement(By.id("nome")).sendKeys(string);
    }

    @Quando("seleciono Salvar")
    public void seleciono_salvar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("recebo a mensagem {string}")
    public void receboAMensagem(String string) {
        String texto =   driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(string, texto);
    }

    @Before
    public void inicio(){
        WebDriverManager.chromedriver().setup();
        System.out.println("Comecamos aqui");
    }

    @After(order = 1)
    public void secreenshot(Scenario cenario){
       File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/sreenshot/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //CASO QUEIRA DIFERENCIAR PARA RODAR EM ALGO ESPECIFICO COLCAR VALUE = "TAG"
    @After(order = 0)
    public  void fecharBrowser(){
        driver.quit();
    }

}

//@Então("a conta é inserida com sucesso")
//    public void a_conta_é_inserida_com_sucesso() {
//     String texto =   driver.findElement(By.xpath("//div[@class=\"alert alert-success\"]")).getText();
//     Assert.assertEquals("Conta adicionada com sucesso!", texto);
//    }
//    @Então("sou notificado que o nome da conta é obrigatório")
//    public void sou_notificar_que_o_nome_da_conta_é_obrigatório() {
//        String texto =   driver.findElement(By.xpath("//div[@class=\"alert alert-danger\"]")).getText();
//        Assert.assertEquals("Informe o nome da conta", texto);
//    }
//    @Então("sou notificado que já existe uma conta com esse nome")
//    public void souNotificadoQueJáExisteUmaContaComEsseNome() {
//        String texto =   driver.findElement(By.xpath("//div[@class=\"alert alert-danger\"]")).getText();
//        Assert.assertEquals("Já existe uma conta com esse nome!", texto);
//    }