package bl;
import JavaBeans.Company;
import java.sql.SQLException;
import java.util.List;
import Exception.CompanyExistsException;

//public class AdminFacade extends bl.ClientFacade {
//
//
//    @Override
//    public boolean login(String email, String password) {
//        return email.equals("admin@admin.com") && password.equals("admin");
//    }
//
//    public void addCompany(Company company) throws SQLException, CompanyExistsException {
//        // check if company.name and company.email do not exist!
//        List<Company> companies = companyDao.getAllCompanies();
//        for(Company comp : companies){
//            if(comp.getName().equals(company.getName())
//                    || comp.getEmail().equals(company.getEmail()) )
//                throw new CompanyExistsException("Company name or email exists");
//        }
//
//        companyDao.addCompany(company);
//    }
//
//    public List<Company> getAllCompanies(){
//        return companyDao.getAllCompanies();
//    }


//}
