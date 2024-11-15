package main;

public class Main {

    public static void main(String[] args) {
        //alternateNumbers();
        System.out.println(isPyramid(134521));  // Expected: true
        System.out.println(isPyramid(1324311)); // Expected: false
    }

    private static void alternateNumbers() {
        int x = 6;
        while (true) {
            System.out.print(x + ",");
            x = 13 - x;
        }
    }

    private static boolean isPyramid(int number) {
        String numStr = String.valueOf(number);
        int n = numStr.length();

        if (n < 3 || numStr.charAt(0) != numStr.charAt(n - 1)) {
            return false;
        }

        int i = 0;
        while (i < n - 1 && numStr.charAt(i) < numStr.charAt(i + 1)) {
            i++;
        }
        if (i == 0 || i == n - 1) {
            return false;
        }

        while (i < n - 1 && numStr.charAt(i) > numStr.charAt(i + 1)) {
            i++;
        }
        return i == n - 1;
    }

}
    /*
questions for the product team:

are there specific response time expectations for each step in the flow?
are there any restrictions on how long data will remain available on the server?
what are the security and authentication requirements for accessing these endpoints?
is there any specific format or criteria for the code value in getdata=<code> requests?
what are the possible error responses if the client submits an invalid or expired code?
what is the server timeout for responses, and how many retries should the client make if it receives a “processing” response?
what checks should be done to ensure the ZIP file and CSV file content are correct and complete?
can you confirm the expected structure of the CSV file in the ZIP file (columns, data types)?
should each interaction (request and response) be stored in the database for traceability? If so, which fields are necessary?

acceptance tests:

verify a valid code produces a zip file with status.txt.
confirm status.txt contains the code
handle cases where the code in status.txt is missing or malformed.
validate server response with the same code if data was not accepted.
verify server returns a new code upon successful acceptance.
ensure “processing” response is given when data is still being prepared.
confirm download location is received when data is ready.
handle downloading, unzipping, and parsing the CSV result file.
test case for invalid or expired codes, expecting proper error responses.
security test cases for unauthorized access to each endpoint.


diagram:

ApiService (@Service)
it manages API requests and responses for the system

getData(String code): sends a GET request to retrieve data and logs the interaction.
postCode(String code): sends a POST request to submit a code and logs the interaction.
downloadFile(String url): handles downloading files when a file location is received.

ApiLogService (@Service)
it logs API requests and responses to a MySQL database.
logRequest(String type, String endpoint, String data): records request details.
logResponse(String response): records response details.


FileProcessor (@Component)
it handles file operations, such as extracting files from ZIPs and validating CSV data.
extractStatusCode(File zipFile): extracts someservercode from status.txt in the ZIP.
validateCSV(File csvFile): Checks the integrity and structure of the CSV data.


ApiLogRepository (@Repository)
Spring Data JPA repository interface for ApiLog entities, providing CRUD operations.


ApiLog (@Entity)
this is an entity representing each API interaction for persistence in the database.
id: a unique identifier.
requestType: Type of the request (e.g., GET, POST).
endpoint: The endpoint used in the request.
requestData: Data sent in the request.
responseData: Data received in the response.
timestamp: Timestamp of the interaction.

TestRunner (@Service)
initiates the testing process by calling ApiService.getData(code) and validates responses

 */
