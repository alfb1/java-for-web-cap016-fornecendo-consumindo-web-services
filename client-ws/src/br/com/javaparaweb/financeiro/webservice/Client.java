package br.com.javaparaweb.financeiro.webservice;

import java.net.URL;
import java.util.Calendar;

public class Client 
{
    public static void main(String[] args)
    {
    	try {
    		URL url = new URL("http://localhost:8080/financeiro/webservice/financeiroWS?wsdl");
    		
    		FinanceiroWSServiceLocator service = new FinanceiroWSServiceLocator();
    		FinanceiroWS financeiroWS = service.getFinanceiroWSPort( new URL( "http://localhost:8080/financeiro/webservice/financeiroWS" ));
    		
    		Calendar inicio = Calendar.getInstance();
    		inicio.add( Calendar.MONTH, -30);
    		Calendar hoje = Calendar.getInstance();
    		
    		int conta = 1;
    		
    		Float saldo = financeiroWS.saldo(conta, hoje);
    		System.out.println("Saldo da conta : " + saldo );
    		System.out.println("========== Lançamentos da Conta ==========");
    		LancamentoItem[] lancamentos = financeiroWS.extrato(conta, inicio, hoje);
    		
    		for( LancamentoItem item : lancamentos) {
    			System.out.println("Data : " + item.getData().getTime() + ". Descricao: " + item.getDescricao() + ". Valor : " + item.getValor());
    		}
    	}catch( Exception e) {
    		System.out.println("Erro : " + e.getMessage());
    	}
    }
}
