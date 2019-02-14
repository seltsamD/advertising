export class User {
  id: number;
  name: string;
  email: string;
  userRole: string;
  active: boolean;

  constructor(email: string, userRole: string) {
    this.email = email;
    this.userRole = userRole;
  }


}
