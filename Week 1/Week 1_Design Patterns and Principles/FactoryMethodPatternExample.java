abstract class Document{
    public abstract void display();
}
class WordDocument extends Document{
    public void display(){
        System.out.println("Word Document");
    }
}

class PdfDocument extends Document{
    public void display(){
        System.out.println("Pdf Document");
    }
}

class ExcelDocument extends Document{
    public void display(){
        System.out.println("Excel Document");
    }
}

abstract class DocumentFactory{
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory{
    public Document createDocument(){
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory{
    public Document createDocument(){
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory{
    public Document createDocument(){
        return new ExcelDocument();
    }
}

class FactoryMethodTest{
    public static void main(String[] args) {
        //Creating Different Factories
        DocumentFactory word_fac=new WordDocumentFactory();
        DocumentFactory pdf_fac=new PdfDocumentFactory();
        DocumentFactory excel_fac=new ExcelDocumentFactory();
        //Creating Documents
        Document word_doc=word_fac.createDocument();
        Document pdf_doc=pdf_fac.createDocument();
        Document excel_doc=excel_fac.createDocument();
        word_doc.display();
        pdf_doc.display();
        excel_doc.display();
    }
}