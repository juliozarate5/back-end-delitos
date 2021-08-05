package co.edu.iudigital.app.service.iface;

public interface IEmailService {
	
	public boolean sendEmail(String mensaje, String email, String asunto);
	
}
