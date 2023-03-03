## Requirements
Should be able to
1. Receive a request for Access Grant Code
2. Send a Login page (or maybe don't, too much trouble ?)
3. Receive a request with consent (or maybe don't, just mock it as true ?)
4. On receiving consent, send a request to redirect url (backend) with Access Grant Code
5. Receive an Access Grant Code + Client Secret + Client ID
6. Validate (5) and send Access Token
7. Provide an endpoint for validating Access token (perhaps used by Resource API)
