package material;

public class Ec extends Material{

    @Override
    protected String loadContent() {
        return """
        The chapters to be studied for the Digital Electronics (DE) Sessional Examination, as per Morris Mano's textbook, are as follows:
1. Chapter 2: BOOLEAN ALGEBRA AND LOGIC GATES
2. Chapter 3: SIMPLIFICATION OF BOOLEAN FUNCTIONS
3. Chapter 4: COMBINATIONAL LOGIC
4. Chapter 5: COMBINATIONAL LOGIC WITH MSI AND LSI
       In Chapter 5, the following topics are included for the sessional exam:
Binary Parallel Adder
Decimal Adder
Magnitude Comparator
Decoders
""";
    } 
}
